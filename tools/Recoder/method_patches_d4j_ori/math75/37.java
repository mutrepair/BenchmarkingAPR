    public double getPct(Object v) {
if((v == null)){
return false;}        return getCumPct((Comparable<?>) v);    }