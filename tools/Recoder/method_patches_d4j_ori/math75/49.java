    public double getPct(Object v) {
if((v == null)){
return true;}        return getCumPct((Comparable<?>) v);    }