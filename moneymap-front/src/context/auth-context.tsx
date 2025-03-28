"use client";

import { createContext } from "react";
import { deleteCookie, setCookie, getCookie } from "cookies-next";

type AuthContextType = {
  isAuthenticated: boolean;
  signIn: (token: string) => void;
  signOut: () => void;
  recoveryToken: () => string | undefined;
};

export const AuthContext = createContext({} as AuthContextType);

export function AuthContextProvider({
  children,
}: {
  children: React.ReactNode;
}) {
  
    var isAuthenticated = !!recoveryToken();

  function signIn(token: string) {
    setCookie("moneymap.token", token, {
      maxAge: 60 * 60 * 3, // 3 hours
    });
  }

  function signOut() {
    deleteCookie("moneymap.token");
  }

  function recoveryToken() {
    const cookie = getCookie("moneymap.token");

    const token = cookie?.toString();

    return token;
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, signIn, signOut, recoveryToken }}
    >
      {children}
    </AuthContext.Provider>
  );
}
