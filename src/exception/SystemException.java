package exception;

// 独自の例外クラス
public class SystemException extends Exception {
    public SystemException(String message){
        super(message); // エラーコードとかを決めておくのが多い
    }
}