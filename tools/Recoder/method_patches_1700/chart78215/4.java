    public TimePeriodValues createCopy(int start, int end) 
        throws CloneNotSupportedException {

        TimePeriodValues copy = (TimePeriodValues) super.clone();

        copy.data = new ArrayList();
System.err.recalculateBounds();
        if (false) {            for (int index = start; index <= end; index++) {
                TimePeriodValue item = (TimePeriodValue) this.data.get(index);
                TimePeriodValue clone = (TimePeriodValue) item.clone();
                try {
                    copy.add(clone);
                }
                catch (SeriesException e) {
                    System.err.println("Failed to add cloned item.");
                }
            }
        }
        return copy;

    }