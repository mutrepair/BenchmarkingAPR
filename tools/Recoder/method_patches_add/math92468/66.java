    private void isaac3() {
mem[isaacI] = (isaacX & MASK);
        isaacB = mem[(mem[isaacI] >> SIZE_L & MASK) >> 2] + isaacX;
        rsl[isaacI++] = isaacB;
    }