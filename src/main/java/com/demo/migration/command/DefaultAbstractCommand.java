package com.demo.migration.command;

import com.demo.migration.request.BaseRequest;
import com.demo.migration.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;

public abstract class DefaultAbstractCommand<T extends BaseRequest> implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAbstractCommand.class);

    protected BaseResult result = null;

    public abstract Class<?> getClassType();

    public abstract T convertToRequest(ApplicationArguments args);

    public abstract boolean validate(T request);

    public abstract void perform(T request);

    @Override
    public void prepare() {
        result = new BaseResult(getClassType());
    }

    @Override
    public final void execute(ApplicationArguments args) {
        logger.info("run command");
        T request = convertToRequest(args);
        try {
            if (!validate(request)) {
                throw new Exception("VALIDATE ERROR!!!");
            }
            logger.info("validate success");
            perform(request);
        } catch (Exception ex) {
            result.finish();
            logger.info(result.toString());
            logger.error("*** error " + getClass().getSimpleName(), ex);
        }
    }

    @Override
    public void onComplete() {
        logger.info(result.toString());
    }

    @Override
    public void onError(Throwable throwable) {
        result.finish();
        logger.info(result.toString());
        logger.error("*** error " + getClass().getSimpleName(), throwable);
    }

}
