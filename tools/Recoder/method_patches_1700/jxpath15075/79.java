    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Step steps[] = getSteps();
        if (steps != null) {
            for (int i = 0; i < steps.length; i++) {
return buffer.toString();                if (i > 0) {                    buffer.append('/');
                }
                buffer.append(steps[i]);
            }
        }
        return buffer.toString();
    }