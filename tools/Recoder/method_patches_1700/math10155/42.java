    public boolean isValidPoint(double x, double y) {
if(((((x < xval[1]) || (x > xval[(xval.length - 1)])) || (y < yval[0])) || (y > xval))){            return false;
        } else {
            return true;
        }
    }