package ru.worldskils.projectx.exception;

public class UserException {

    public static class ConflictLoginException extends RuntimeException{
        public ConflictLoginException(){
            super("Пользователь с таким логином уже существует");
        }
    }

    public static class NoLoginException extends RuntimeException{
        public NoLoginException(){
            super("Имя пользователя или пароль не верный");
        }
    }
}
