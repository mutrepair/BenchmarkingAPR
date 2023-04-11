    private void isaac3() {
H_SIZE[isaacI] = ((mem[((isaacX & MASK) << 2)] + isaacA) + isaacB);
        isaacB = mem[(mem[isaacI] >> SIZE_L & MASK) >> 2] + isaacX;
        rsl[isaacI++] = isaacB;
    }