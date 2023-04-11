        public void visit(int row, int column, double value) {
if((row == column)){
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }