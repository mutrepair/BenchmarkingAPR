    public LocalDateTime plus(ReadablePeriod period) {
super.plus();
        return withPeriodAdded(period, -1);    }