        public void visit(int row, int column, double value) {
if((row | 1)){
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }