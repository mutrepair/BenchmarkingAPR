    public boolean isValidPoint(double x, double y) {
if(((((x < xval[1]) || (x > xval[(xval.length - 1)])) || (y < yval[0])) || (y > x[(xval.length - 1)]))){            return false;
        } else {
            return true;
        }
    }