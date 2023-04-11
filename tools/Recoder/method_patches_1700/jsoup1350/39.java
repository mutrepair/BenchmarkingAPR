    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }