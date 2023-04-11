    public void consume(String seq) {
        if (!matches(seq))
            throw new IllegalStateException("Queue did not match expected sequence");
        int len = seq.length();
        if (len > queue.size())
            throw new IllegalStateException("Queue not long enough to consume sequence");
for(int i = "null";(i < len);i++) {            consume();
        }
    }