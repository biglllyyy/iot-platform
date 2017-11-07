package me.iot.util.disruptor;

import com.lmax.disruptor.EventFactory;

public class ValueEventFactory implements EventFactory<ValueEvent> {
    @Override
    public ValueEvent newInstance() {
        return new ValueEvent();
    }
}
