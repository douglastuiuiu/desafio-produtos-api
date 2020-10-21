package br.com.douglasog87.event.strategy;

import br.com.douglasog87.domain.Payload;
import br.com.douglasog87.event.DomainEvent;
import br.com.douglasog87.event.EventStrategy;
import br.com.douglasog87.event.EventType;

public enum ProductEvent implements EventType, EventStrategy {

    CREATE {
        @Override
        public <T extends Payload, E extends EventType> DomainEvent newInstance(T payload) {
            return DomainEvent.builder()
                    .payload(payload)
                    .type(this)
                    .build();
        }
    },

    UPDATE {
        @Override
        public <T extends Payload, E extends EventType> DomainEvent newInstance(T payload) {
            return DomainEvent.builder()
                    .payload(payload)
                    .type(this)
                    .build();
        }
    },

    DELETE {
        @Override
        public <T extends Payload, E extends EventType> DomainEvent newInstance(T payload) {
            return DomainEvent.builder()
                    .payload(payload)
                    .type(this)
                    .build();
        }
    }
}
