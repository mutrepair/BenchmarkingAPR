private final int jjMoveStringLiteralDfa19_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(17, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(18, active0, 0L);
      return 19;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x800000000000L) != 0L)
return jjStopAtPos(19, jjnewStateCnt);         break;
      case 105:
         return jjMoveStringLiteralDfa20_0(active0, 0x400000000L);
      default :
         break;
   }
   return jjStartNfa_0(18, active0, 0L);
}