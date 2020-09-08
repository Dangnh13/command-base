package com.demo.migration.command;

import org.springframework.boot.ApplicationArguments;

public interface ICommand {

	default void prepare() {
	}

	void execute(ApplicationArguments args) throws Exception;

	default void onComplete() {
	}

	default void onError(Throwable throwable) {
	}
}
