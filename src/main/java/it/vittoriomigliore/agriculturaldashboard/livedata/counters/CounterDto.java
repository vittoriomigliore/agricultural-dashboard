package it.vittoriomigliore.agriculturaldashboard.livedata.counters;

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

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getChange() {
        return change;
    }

    public void setChange(Integer change) {
        this.change = change;
    }
}
