        public void visit(int row, int column, double value) {
if(((row == column) && ((row | 1) == 0))){
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }