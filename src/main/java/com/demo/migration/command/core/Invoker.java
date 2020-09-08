package com.demo.migration.command.core;

import com.demo.migration.command.ICommand;
import com.demo.migration.command.type.CommandID;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Invoker implements BeanPostProcessor {

    protected ConcurrentMap<CommandID, ICommand> channelCommands = new ConcurrentHashMap<>();

    private ApplicationContext context;

    public Invoker(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean.getClass().isAnnotationPresent(MigrationCommand.class) && (bean instanceof ICommand)) {
            CommandID id = bean.getClass().getAnnotation(MigrationCommand.class).value();
            channelCommands.put(id, (ICommand) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    public void invoke(ClientRequest clientRequest) {
        ICommand runner = channelCommands.get(clientRequest.getCommandID());
        int exitCode = 0;

        try {
            runner.prepare();
            runner.execute(null);
            runner.onComplete();

        } catch (Exception e) {
            exitCode = 1;
            runner.onError(e);

        } finally {
            SpringApplication.exit(context);
            System.exit(exitCode);
        }
    }

}
