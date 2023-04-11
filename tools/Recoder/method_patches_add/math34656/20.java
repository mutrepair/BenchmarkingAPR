    public T getAngle() {
if(((q0.getReal() < +0.1) && (q0.getReal() > 0.1))){
            return q1.multiply(q1).add(q2.multiply(q2)).add(q3.multiply(q3)).sqrt().asin().multiply(2);
        } else if (q0.getReal() < 0) {
            return q0.negate().acos().multiply(2);
        }
        return q0.acos().multiply(2);
    }