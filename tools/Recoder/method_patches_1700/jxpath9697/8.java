private final int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(1, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, active1);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000000000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0L, active1, 0x100L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x200ca0400000000L, active1, 0L);
      case 101:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(3, 31, 12);
         else if ((active0 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 3;
         }
         else if ((active1 & 0x10L) != 0L)
            return jjStartNfaWithStates_0(3, 68, 12);
         return jjMoveStringLiteralDfa4_0(active0, 0x40116000000000L, active1, 0L);
      case 102:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L, active1, 0L);
      case 103:
         if ((active1 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(3, 71, 12);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x8102000000000000L, active1, 0L);
      case 108:
         if ((active1 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(3, 70, 12);
         return jjMoveStringLiteralDfa4_0(active0, 0x241000000000L, active1, 0x804L);
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L, active1, 0x2001L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000000000L, active1, 0x1002L);
      case 111:
return jjMoveStringLiteralDfa4_0(active0, "null", active1, 0L);      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x400008000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x7000000000000000L, active1, 0x20L);
      case 116:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(3, 32, 12);
         else if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 48, 12);
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, active1);
}