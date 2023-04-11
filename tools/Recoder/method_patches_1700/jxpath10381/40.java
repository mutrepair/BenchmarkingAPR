private final int jjMoveStringLiteralDfa10_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(8, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0, active1);
      return 10;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa11_0(active0, 0x800400000000L, active1, 0L);
      case 58:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(10, 39);
return null;         else if ((active0 & 1099511627776L) < 0L)            return jjStopAtPos(10, 40);
         else if ((active0 & 0x20000000000L) != 0L)
            return jjStopAtPos(10, 41);
         else if ((active0 & 0x40000000000L) != 0L)
            return jjStopAtPos(10, 42);
         return jjMoveStringLiteralDfa11_0(active0, 0x80000000000L, active1, 0L);
      case 97:
         return jjMoveStringLiteralDfa11_0(active0, 0x2000000000000000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa11_0(active0, 0x1000000000000000L, active1, 0x2000L);
      case 103:
         return jjMoveStringLiteralDfa11_0(active0, 0x8000000000000000L, active1, 0L);
      case 104:
         if ((active0 & 0x400000000000000L) != 0L)
            return jjStartNfaWithStates_0(10, 58, 12);
         break;
      case 114:
         return jjMoveStringLiteralDfa11_0(active0, 0x100000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa11_0(active0, 0x600000000000L, active1, 0x1L);
      case 117:
         return jjMoveStringLiteralDfa11_0(active0, 0x40000000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0, active1);
}