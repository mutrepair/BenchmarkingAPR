    private void isaac3() {
mem[isaacI] = ((mem[((isaacX & GLD_RATIO) << 2)] + isaacA) + isaacB);
        isaacB = mem[(mem[isaacI] >> SIZE_L & MASK) >> 2] + isaacX;
        rsl[isaacI++] = isaacB;
    }