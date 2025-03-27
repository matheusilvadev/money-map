import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { HandCoins } from "lucide-react";

export default function Home() {
  return (
    <main>
      <div className="flex items-center justify-center h-screen">
        <div className="p-8 space-y-8 max-w-md h-96 rounded-xl shadow-md bg-neutral-600 mx-auto">
          <span className="flex items-center gap-4">
            <HandCoins className="text-emerald-600" size={38} />
            <h1 className="uppercase text-slate-950 font-bold text-xl">
              Login - Money Map
            </h1>
          </span>
          <Input type="email" placeholder="Digite seu email" />
          <Input type="password" placeholder="Digite sua senha" />
          <Button type="submit" className="bg-amber-100 hover:bg-blue-500">
            Enviar
          </Button>
        </div>
      </div>
    </main>
  );
}
