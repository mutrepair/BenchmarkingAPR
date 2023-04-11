    public LocalDateTime plus(ReadablePeriod period) {
if((period == 0)){
return null;}        return withPeriodAdded(period, -1);    }