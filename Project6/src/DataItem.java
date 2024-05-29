////////////////////////////////////////////////////////////////
public class DataItem
{
    private String iData;
    //--------------------------------------------------------------
    public DataItem(String ii)          // constructor
    {
        iData = ii;
    }
    //--------------------------------------------------------------
    public String getKey()
    { return iData; }

    public DataItem getItem() {
        return new DataItem(iData);
    }
//--------------------------------------------------------------
}  // end class DataItem
////////////////////////////////////////////////////////////////