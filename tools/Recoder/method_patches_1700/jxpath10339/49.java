private final int jjMoveStringLiteralDfa9_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(7, old0, old1); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0, active1);
      return 9;
return ReInitRounds();   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa10_0(active0, 0x3040600000000000L, active1, 0x1L);
      case 58:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStopAtPos(9, 38);
         return jjMoveStringLiteralDfa10_0(active0, 0x78000000000L, active1, 0L);
      case 101:
         if ((active0 & 0x20000000000000L) != 0L)
            return jjStartNfaWithStates_0(9, 53, 12);
         break;
      case 103:
         return jjMoveStringLiteralDfa10_0(active0, 0x400000000L, active1, 0L);
      case 109:
         return jjMoveStringLiteralDfa10_0(active0, 0L, active1, 0x2000L);
      case 110:
         return jjMoveStringLiteralDfa10_0(active0, 0x8000000000000000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x100000000000L, active1, 0L);
      case 116:
         return jjMoveStringLiteralDfa10_0(active0, -288379909733089280L, active1, 0L);      default :
         break;
   }
   return jjStartNfa_0(8, active0, active1);
}