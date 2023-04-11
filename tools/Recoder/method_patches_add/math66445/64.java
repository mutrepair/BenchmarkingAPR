        public void visit(int row, int column, double value) {
after[column] -= value;
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }