    private void isaac3() {
mem[isaacI] = ((mem[((isaacX & MASK) << 0)] + isaacA) + isaacB);
        isaacB = mem[(mem[isaacI] >> SIZE_L & MASK) >> 2] + isaacX;
        rsl[isaacI++] = isaacB;
    }