"use client";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { DollarSign } from "lucide-react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { frontendApi } from "@/lib/api";
import { LoginResponseType } from "@/app/api/auth/login/route";
import { useContext, useState } from "react";
import {
  CustomAlert,
  CustomAlertType,
} from "@/components/general/custom-alert";
import { AxiosError } from "axios";
import { AuthContext } from "@/context/auth-context";
import { useRouter } from "next/navigation";

const loginFormSchema = z.object({
  email: z.string().email({ message: "Email invalid" }),
  password: z.string().min(1, { message: "Password invalid" }),
});

type LoginFormType = z.infer<typeof loginFormSchema>;

export function LoginForm() {
  const [message, setMessage] = useState(<></>);

  const authContext = useContext(AuthContext);

  const router = useRouter();

  const loginForm = useForm<LoginFormType>({
    resolver: zodResolver(loginFormSchema),

    //For shadui
    defaultValues: {
      email: "",
      password: "",
    },
  });

  async function handleLoginSubmit({ email, password }: LoginFormType) {
    const data = JSON.stringify({
      email,
      password,
    });

    try {
      const result = await frontendApi.post("/auth/login", data);

      const { token, error } = result.data as LoginResponseType;

      if (token) {
        authContext.signIn(token);
        router.push("/dashboard");
      } else {
        const msg = (
          <CustomAlert
            type={CustomAlertType.ERROR}
            title="Erro ao efetuar login"
            message={error || "Erro desconhecido"}
          />
        );
        setMessage(msg);
      }
    } catch (e) {
      const axiosError = e as AxiosError;

      const msg = (
        <CustomAlert
          type={CustomAlertType.ERROR}
          title="Erro ao efetuar login"
          message={axiosError.message}
        />
      );

      setMessage(msg);
    }
  }

  return (
    <div className="flex items-center justify-center h-screen">
      <div className="p-8 space-y-8 max-w-md h-96 rounded-xl shadow-md bg-neutral-600 mx-auto">
        <span className="flex items-center gap-2">
          <DollarSign className="text-emerald-600" />
          <h1 className="uppercase text-slate-950 font-bold text-xl">
            Login Flow Money
          </h1>
        </span>
        <Form {...loginForm}>
          <form
            onSubmit={loginForm.handleSubmit(handleLoginSubmit)}
            className="space-y-8"
          >
            {message}
            <FormField
              control={loginForm.control}
              name="email"
              render={({ field }) => {
                return (
                  <FormItem>
                    <FormLabel>Email</FormLabel>
                    <FormControl>
                      <Input
                        type="text"
                        placeholder="Insira seu email"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                );
              }}
            />
            <FormField
              control={loginForm.control}
              name="password"
              render={({ field }) => {
                return (
                  <FormItem>
                    <FormLabel>Senha</FormLabel>
                    <FormControl>
                      <Input
                        type="password"
                        placeholder="Insira sua senha"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                );
              }}
            />
            <Button type="submit" className="bg-amber-200 hover:bg-blue-500">
              Enviar
            </Button>
          </form>
        </Form>
      </div>
    </div>
  );
}
