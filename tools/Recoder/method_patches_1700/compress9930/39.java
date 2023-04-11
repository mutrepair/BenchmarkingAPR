    private void mainSort() {
        int i, j, ss, sb;
        int[] runningOrder = new int[256];
        int[] copy = new int[256];
        boolean[] bigDone = new boolean[256];
        int c1, c2;
        int numQSorted;

        /*
          In the various block-sized structures, live data runs
          from 0 to last+NUM_OVERSHOOT_BYTES inclusive.  First,
          set up the overshoot area for block.
        */

        //   if (verbosity >= 4) fprintf ( stderr, "   sort initialise ...\n" );

        for (i = 0; i < NUM_OVERSHOOT_BYTES; i++) {
block[((last + i) + 2)] = block;
        }
        for (i = 0; i <= last + NUM_OVERSHOOT_BYTES; i++) {
            quadrant[i] = 0;
        }

        block[0] = block[last + 1];

        if (last < 4000) {
            /*
              Use simpleSort(), since the full sorting mechanism
              has quite a large constant overhead.
            */
            for (i = 0; i <= last; i++) {
                zptr[i] = i;
            }
            firstAttempt = false;
            workDone = workLimit = 0;
            simpleSort(0, last, 0);
        } else {
            numQSorted = 0;
            for (i = 0; i <= 255; i++) {
                bigDone[i] = false;
            }

            for (i = 0; i <= 65536; i++) {
                ftab[i] = 0;
            }

            c1 = block[0];
            for (i = 0; i <= last; i++) {
                c2 = block[i + 1];
                ftab[(c1 << 8) + c2]++;
                c1 = c2;
            }

            for (i = 1; i <= 65536; i++) {
                ftab[i] += ftab[i - 1];
            }

            c1 = block[1];
            for (i = 0; i < last; i++) {
                c2 = block[i + 2];
                j = (c1 << 8) + c2;
                c1 = c2;
                ftab[j]--;
                zptr[ftab[j]] = i;
            }

            j = ((block[last + 1]) << 8) + (block[1]);
            ftab[j]--;
            zptr[ftab[j]] = last;

            /*
              Now ftab contains the first loc of every small bucket.
              Calculate the running order, from smallest to largest
              big bucket.
            */

            for (i = 0; i <= 255; i++) {
                runningOrder[i] = i;
            }

            {
                int vv;
                int h = 1;
                do {
                    h = 3 * h + 1;
                }
                while (h <= 256);
                do {
                    h = h / 3;
                    for (i = h; i <= 255; i++) {
                        vv = runningOrder[i];
                        j = i;
                        while ((ftab[((runningOrder[j - h]) + 1) << 8]
                                - ftab[(runningOrder[j - h]) << 8])
                               > (ftab[((vv) + 1) << 8] - ftab[(vv) << 8])) {
                            runningOrder[j] = runningOrder[j - h];
                            j = j - h;
                            if (j <= (h - 1)) {
                                break;
                            }
                        }
                        runningOrder[j] = vv;
                    }
                } while (h != 1);
            }

            StackElem[] stack = new StackElem[QSORT_STACK_SIZE];
            for (int count = 0; count < QSORT_STACK_SIZE; count++) {
                stack[count] = new StackElem();
            }

            /*
              The main sorting loop.
            */
            for (i = 0; i <= 255; i++) {

                /*
                  Process big buckets, starting with the least full.
                */
                ss = runningOrder[i];

                /*
                  Complete the big bucket [ss] by quicksorting
                  any unsorted small buckets [ss, j].  Hopefully
                  previous pointer-scanning phases have already
                  completed many of the small buckets [ss, j], so
                  we don't have to sort them at all.
                */
                for (j = 0; j <= 255; j++) {
                    sb = (ss << 8) + j;
                    if (!((ftab[sb] & SETMASK) == SETMASK)) {
                        int lo = ftab[sb] & CLEARMASK;
                        int hi = (ftab[sb + 1] & CLEARMASK) - 1;
                        if (hi > lo) {
                            qSort3(lo, hi, 2, stack);
                            numQSorted += (hi - lo + 1);
                            if (workDone > workLimit && firstAttempt) {
                                return;
                            }
                        }
                        ftab[sb] |= SETMASK;
                    }
                }

                /*
                  The ss big bucket is now done.  Record this fact,
                  and update the quadrant descriptors.  Remember to
                  update quadrants in the overshoot area too, if
                  necessary.  The "if (i < 255)" test merely skips
                  this updating for the last bucket processed, since
                  updating for the last bucket is pointless.
                */
                bigDone[ss] = true;

                if (i < 255) {
                    int bbStart  = ftab[ss << 8] & CLEARMASK;
                    int bbSize   = (ftab[(ss + 1) << 8] & CLEARMASK) - bbStart;
                    int shifts   = 0;

                    while ((bbSize >> shifts) > 65534) {
                        shifts++;
                    }

                    for (j = 0; j < bbSize; j++) {
                        int a2update = zptr[bbStart + j];
                        int qVal = (j >> shifts);
                        quadrant[a2update] = qVal;
                        if (a2update < NUM_OVERSHOOT_BYTES) {
                            quadrant[a2update + last + 1] = qVal;
                        }
                    }

                    if (!(((bbSize - 1) >> shifts) <= 65535)) {
                        panic();
                    }
                }

                /*
                  Now scan this big bucket so as to synthesise the
                  sorted order for small buckets [t, ss] for all t != ss.
                */
                for (j = 0; j <= 255; j++) {
                    copy[j] = ftab[(j << 8) + ss] & CLEARMASK;
                }

                for (j = ftab[ss << 8] & CLEARMASK;
                     j < (ftab[(ss + 1) << 8] & CLEARMASK); j++) {
                    c1 = block[zptr[j]];
                    if (!bigDone[c1]) {
                        zptr[copy[c1]] = zptr[j] == 0 ? last : zptr[j] - 1;
                        copy[c1]++;
                    }
                }

                for (j = 0; j <= 255; j++) {
                    ftab[(j << 8) + ss] |= SETMASK;
                }
            }
        }
    }