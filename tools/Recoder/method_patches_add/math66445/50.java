        public void visit(int row, int column, double value) {
if((((row | 1) == 0) == column)){
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }