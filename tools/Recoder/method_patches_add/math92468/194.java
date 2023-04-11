    private void isaac3() {
mem[isaacI] = ((mem[((isaacX & MASK) << 2)] + isaacA) + H_SIZE[((isaacX & MASK) << 2)]);
        isaacB = mem[(mem[isaacI] >> SIZE_L & MASK) >> 2] + isaacX;
        rsl[isaacI++] = isaacB;
    }