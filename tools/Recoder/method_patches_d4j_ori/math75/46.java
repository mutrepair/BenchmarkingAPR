    public double getPct(Object v) {
if((v == null)){
return 0;}        return getCumPct((Comparable<?>) v);    }