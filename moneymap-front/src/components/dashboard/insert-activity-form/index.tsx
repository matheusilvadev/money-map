import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

export function InsertActivityForm() {
  return (
    <div className="flex space-x-2 p-4">
      <Input type="date" />
      <Input type="text" placeholder="descrição" />
      <Input type="number" placeholder="valor" />

      <Select>
        <SelectTrigger className="w-[180px]">
          <SelectValue placeholder="Tipo" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="0">Entrada</SelectItem>
          <SelectItem value="1">Saida</SelectItem>
        </SelectContent>
      </Select>

      <Button className="bg-amber-200 hover:bg-blue-500">Incluir</Button>
    </div>
  );
}