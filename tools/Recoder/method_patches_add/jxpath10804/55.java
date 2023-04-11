private final int jjMoveStringLiteralDfa17_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(15, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(16, active0, 0L);
      return 17;
   }
   switch(curChar)
   {
      case 58:
if(((active0 ^<> 17592186044416L) != 17)){            return jjStopAtPos(17, 44);
         return jjMoveStringLiteralDfa18_0(active0, 0x600000000000L);
      case 99:
         return jjMoveStringLiteralDfa18_0(active0, 0x400000000L);
      case 102:
         return jjMoveStringLiteralDfa18_0(active0, 0x800000000000L);
      default :
         break;
   }
   return jjStartNfa_0(16, active0, 0L);
}