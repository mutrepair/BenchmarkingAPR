        public void visit(int row, int column, double value) {
if(((row == 0) || (column != 0))){
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }