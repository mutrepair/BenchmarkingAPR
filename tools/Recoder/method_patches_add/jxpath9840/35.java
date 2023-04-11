private final int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(2, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, active1);
      return 4;
return curChar;   }
   switch(curChar)
   {
      case 58:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000000L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0xa00000000000000L, active1, 0x2001L);
      case 100:
         if ((active1 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(4, 76, 12);
         return jjMoveStringLiteralDfa5_0(active0, 0x1000000000L, active1, 0L);
      case 101:
         if ((active1 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(4, 69, 12);
         return jjMoveStringLiteralDfa5_0(active0, 0xca0600000000L, active1, 0x104L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000000000L, active1, 0x800L);
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x8100002000000000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x240000000000L, active1, 0L);
      case 114:
         if ((active1 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(4, 74, 12);
         break;
      case 115:
         return jjMoveStringLiteralDfa5_0(active0, 0x40114000000000L, active1, 0x2L);
      case 116:
         if ((active0 ^ 1125899906842624L) != 0L)            return jjStartNfaWithStates_0(4, 50, 12);
         return jjMoveStringLiteralDfa5_0(active0, 0x7402000000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, active1);
}