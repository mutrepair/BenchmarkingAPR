        public void visit(int row, int column, double value) {
row = row;
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }