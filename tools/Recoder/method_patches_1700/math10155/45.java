    public boolean isValidPoint(double x, double y) {
if(((((x < xval[1]) || (x > xval[(xval.length - 1)])) || (y < xval[0])) || (y > yval[(yval.length - 1)]))){            return false;
        } else {
            return true;
        }
    }