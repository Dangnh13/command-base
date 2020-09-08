package com.demo.migration.command.concrete;

import com.demo.migration.command.DefaultAbstractCommand;
import com.demo.migration.command.core.MigrationCommand;
import com.demo.migration.command.type.CommandID;
import com.demo.migration.request.MC0001Request;
import org.springframework.boot.ApplicationArguments;

@MigrationCommand(CommandID.MC0001)
public class MC0001Command extends DefaultAbstractCommand<MC0001Request> {

    @Override
    public Class<?> getClassType() {
        return this.getClass();
    }

    @Override
    public MC0001Request convertToRequest(ApplicationArguments args) {
        return new MC0001Request();
    }

    @Override
    public boolean validate(MC0001Request request) {
        return true;
    }

    @Override
    public void perform(MC0001Request request) {
        System.out.println("Perform in MC0001 Command");
    }

}
