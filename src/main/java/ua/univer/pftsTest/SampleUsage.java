import ua.pfts.midlay.Utils;
import ua.univer.pftsTest.config.ConfigProperties;


public class SampleUsage {
    //path to  key file
    private static final String KEY_FILE = ConfigProperties.KEY_PATH;
    //test password
    private static final String PWD = "qwerty123";
    //test tran data
    private static final String TRAN_DATA = "<LOGON pwd=\"P8c8Upu.UMEHY\" lang=\"E\" agent=\"Tester\" ver=\"1.0.0.0\" signkey=\"1234\" /> ";

    public static void main(String[] args) {
        //instance ua.pfts.midlay.Utils
        final Utils utils = new Utils();
        try {

            System.out.println("--------------GETS KEY ID----------------------------------");

            final int result = utils.getKeyId(KEY_FILE);
            System.out.println(" Key Id = " + result);

            System.out.println("---------------ENCRYPT PASSWORD----------------------------");

            final String encryptedPwd = utils.encryptPassword(PWD);
            System.out.println(" " + PWD + " -> " + encryptedPwd);

            System.out.println("----------------SIGN TRAN DATA-----------------------------");

            final String _result = utils.signatureData(KEY_FILE, TRAN_DATA);
            System.out.println(" Sign = " + _result);

            System.out.println("--------------PRINT KEY INFO-------------------------------");

            utils.printKeyInfo(KEY_FILE);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
//     Output:
//--------------GETS KEY ID----------------------------------
//    Key Id = 3600
//---------------ENCRYPT PASSWORD----------------------------
//    qwerty123 -> @OsK1D8Jbdaee
//----------------SIGN TRAN DATA-----------------------------
//    Sign = B9CC9DF5E8919B3CB04A3E400885F188D6D98DDD68EE4C995D8912EE6904648E
//--------------PRINT KEY INFO-------------------------------
//    User Name - USER00001
//    User Firm - USER
//    Key Id - 3600
//    Valid from 21.05.2025 to 21.05.2026
//    Body - 839AAC6B6E017421FD4FAA3668284DF3492A859C56803CD64BFD85777C1A7F02


}
