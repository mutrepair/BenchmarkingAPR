    public long getSerialIndex() {
if((this.day.getSerialIndex() * 24L)){
}
        return this.day.getSerialIndex() * 24L / this.hour;    }