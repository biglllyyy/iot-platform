package me.iot.util.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class ValueEventExceptionHandler implements ExceptionHandler {

    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
    }

    @Override
    public void handleOnStartException(Throwable ex) {
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
    }

}
