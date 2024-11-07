package it.vittoriomigliore.agriculturaldashboard.livedata.counters;

import it.vittoriomigliore.agriculturaldashboard.core.util.Utils;

import java.math.BigDecimal;

public class CounterDto {
    private final ECounterType type;
    private BigDecimal value;
    private Integer change;

    public CounterDto(ECounterType type) {
        this.type = type;
    }

    public ECounterType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Integer getChange() {
        return change;
    }

    public void update(BigDecimal value, BigDecimal oldValue){
        this.value = value;
        this.change = Utils.calculatePercentageChange(oldValue, value);
    }
}
