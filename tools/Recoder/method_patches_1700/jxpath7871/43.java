private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active1 & 0xc0000L) != 0L)
            return 10;
         if ((active0 & 0xfffffffff8000000L) != 0L || (active1 & 0x3fffL) != 0L)
         {
if(((active1 & 0x3fffL) != 0L)){
            jjmatchedKind = -78;            return 12;
}
         }
         return -1;
      case 1:
         if ((active0 & 0x8000008000000L) != 0L)
            return 12;
         if ((active0 & 0xfff7fffff0000000L) != 0L || (active1 & 0x3fffL) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 1;
            return 12;
         }
         return -1;
      case 2:
         if ((active0 & 0x10000070000000L) != 0L || (active1 & 0x208L) != 0L)
            return 12;
         if ((active0 & 0xffe7ffff80000000L) != 0L || (active1 & 0x3df7L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 2;
            return 12;
         }
         return -1;
      case 3:
         if ((active0 & 0xc1010180000000L) != 0L || (active1 & 0xd0L) != 0L)
            return 12;
         if ((active0 & 0xff26fefe00000000L) != 0L || (active1 & 0x3d27L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 3;
            }
            return 12;
         }
         return -1;
      case 4:
         if ((active0 & 0xff62fff600000000L) != 0L || (active1 & 0x2907L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 4;
            return 12;
         }
         if ((active0 & 0x4000000000000L) != 0L || (active1 & 0x1420L) != 0L)
            return 12;
         if ((active0 & 0x800000000L) != 0L)
         {
            if (jjmatchedPos < 3)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 3;
            }
            return -1;
         }
         return -1;
      case 5:
         if ((active0 & 0x8300000000000000L) != 0L || (active1 & 0x100L) != 0L)
            return 12;
         if ((active0 & 0x7c62ffe600000000L) != 0L || (active1 & 0x2807L) != 0L)
         {
            if (jjmatchedPos != 5)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 5;
            }
            return 12;
         }
         if ((active0 & 0x1000000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0x800000000L) != 0L)
         {
            if (jjmatchedPos < 3)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 3;
            }
            return -1;
         }
         return -1;
      case 6:
         if ((active0 & 0x200000000L) != 0L || (active1 & 0x804L) != 0L)
            return 12;
         if ((active0 & 0x2000000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 5;
            }
            return -1;
         }
         if ((active0 & 0x1000000000L) != 0L)
         {
            if (jjmatchedPos < 4)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 4;
            }
            return -1;
         }
         if ((active0 & 0xfc62ffc400000000L) != 0L || (active1 & 0x2003L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 6;
            return 12;
         }
         return -1;
      case 7:
         if ((active0 & 0xf460ffc400000000L) != 0L || (active1 & 0x2003L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 7;
            return 12;
         }
         if ((active0 & 0x802000000000000L) != 0L)
            return 12;
         if ((active0 & 0x2000000000L) != 0L)
         {
            if (jjmatchedPos < 5)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 5;
            }
            return -1;
         }
         return -1;
      case 8:
         if ((active0 & 0x7000000000000000L) != 0L || (active1 & 0x2L) != 0L)
            return 12;
         if ((active0 & 0x4000000000L) != 0L)
         {
            if (jjmatchedPos < 7)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 7;
            }
            return -1;
         }
         if ((active0 & 0x8460ff8400000000L) != 0L || (active1 & 0x2001L) != 0L)
         {
            if (jjmatchedPos != 8)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 8;
            }
            return 12;
         }
         return -1;
      case 9:
         if ((active0 & 0x20000000000000L) != 0L)
            return 12;
         if ((active0 & 0x78000000000L) != 0L)
         {
            if (jjmatchedPos < 8)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 8;
            }
            return -1;
         }
         if ((active0 & 0x4000000000L) != 0L)
         {
            if (jjmatchedPos < 7)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 7;
            }
            return -1;
         }
         if ((active0 & 0xb440f80400000000L) != 0L || (active1 & 0x2001L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 9;
            return 12;
         }
         return -1;
      case 10:
         if ((active0 & 0x400000000000000L) != 0L)
            return 12;
         if ((active0 & 0x80000000000L) != 0L)
         {
            if (jjmatchedPos < 9)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 9;
            }
            return -1;
         }
         if ((active0 & 0x78000000000L) != 0L)
         {
            if (jjmatchedPos < 8)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 8;
            }
            return -1;
         }
         if ((active0 & 0xb040f00400000000L) != 0L || (active1 & 0x2001L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 10;
            return 12;
         }
         return -1;
      case 11:
         if ((active0 & 0xb040f00400000000L) != 0L || (active1 & 0x2001L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 11;
            return 12;
         }
         if ((active0 & 0x80000000000L) != 0L)
         {
            if (jjmatchedPos < 9)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 9;
            }
            return -1;
         }
         return -1;
      case 12:
         if ((active0 & 0x8040000000000000L) != 0L || (active1 & 0x2000L) != 0L)
            return 12;
         if ((active0 & 0x3000f00400000000L) != 0L || (active1 & 0x1L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 12;
            return 12;
         }
         return -1;
      case 13:
         if ((active0 & 0x3000f00400000000L) != 0L || (active1 & 0x1L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 13;
            return 12;
         }
         return -1;
      case 14:
         if ((active0 & 0x2000000000000000L) != 0L || (active1 & 0x1L) != 0L)
            return 12;
         if ((active0 & 0x1000f00400000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 14;
            return 12;
         }
         return -1;
      case 15:
         if ((active0 & 0x1000000000000000L) != 0L)
            return 12;
         if ((active0 & 0xf00400000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 15;
            return 12;
         }
         return -1;
      case 16:
         if ((active0 & 0xe00400000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 16;
            return 12;
         }
         if ((active0 & 0x100000000000L) != 0L)
         {
            if (jjmatchedPos < 15)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 15;
            }
            return -1;
         }
         return -1;
      case 17:
         if ((active0 & 0x600000000000L) != 0L)
         {
            if (jjmatchedPos < 16)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 16;
            }
            return -1;
         }
         if ((active0 & 0x100000000000L) != 0L)
         {
            if (jjmatchedPos < 15)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 15;
            }
            return -1;
         }
         if ((active0 & 0x800400000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 17;
            return 12;
         }
         return -1;
      case 18:
         if ((active0 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 18;
            return 12;
         }
         if ((active0 & 0x800000000000L) != 0L)
         {
            if (jjmatchedPos < 17)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 17;
            }
            return -1;
         }
         if ((active0 & 0x600000000000L) != 0L)
         {
            if (jjmatchedPos < 16)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 16;
            }
            return -1;
         }
         return -1;
      case 19:
         if ((active0 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 19;
            return 12;
         }
         if ((active0 & 0x800000000000L) != 0L)
         {
            if (jjmatchedPos < 17)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 17;
            }
            return -1;
         }
         return -1;
      case 20:
         if ((active0 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 20;
            return 12;
         }
         return -1;
      default :
         return -1;
   }
}