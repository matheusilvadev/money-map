import { Activity, columns } from "./columns";
import { DataTable } from "./data-tabele";

const data: Activity[] = [
  {
    id: "1",
    date: new Date("07-25-2023"),
    description: "Pagamento de conta",
    value: 250.95,
    type: "expense",
  },
  {
    id: "2",
    date: new Date("07-13-2023"),
    description: "Pagamento de conta",
    value: 99.95,
    type: "expense",
  },
  {
    id: "3",
    date: new Date("07-15-2023"),
    description: "Salario",
    value: 2000.95,
    type: "revenue",
  },
];

function getData(): Activity[] {
  // Fetch data from your API here.
  return data;
}

export function ActivityTable() {
  const data = getData();

  return (
    <div className="container mx-auto py-10">
      <DataTable columns={columns} data={data} />
    </div>
  );
}
