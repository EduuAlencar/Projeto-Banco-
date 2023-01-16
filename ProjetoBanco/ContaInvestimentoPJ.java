package ProjetoBanco;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class ContaInvestimentoPJ extends PessoaJuridica {
    Scanner input = new Scanner(System.in);
    Random randomId = new Random();
    boolean sair;

    // GERAR NUMERO ALEATORIO PARA A CONTA
    int numeroContaAleatorio = randomId.nextInt(999999);
    private int idConta = randomId.nextInt(10);
    private String _numConta = String.valueOf(numeroContaAleatorio) + "-" + idConta;

    private Date _dataAtual = new Date();

    final private String _operacao = "230";
    final private String _agencia = "145";
    private double _saldo;


    //--------------------------- GET E SET ------------------------------------------------
    public String get_numConta() {
        return _numConta;
    }

    public void set_numConta(String numConta) {
        this._numConta = numConta;
    }

    public double get_saldo() {
        return _saldo;
    }

    public void set_saldo(double _saldo) {
        this._saldo = _saldo;
    }

    public String get_operacao() {
        return _operacao;
    }

    public String get_agencia() {
        return _agencia;
    }

    public Date getDataAtual() {
        return _dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this._dataAtual = dataAtual;
    }


    //--------------------------- CONSTRUCTOR ------------------------------------------------
    public ContaInvestimentoPJ(String cnpj, String razaoSocial, String nomeFantasia, String dataDeAbertura, String endereco, String telefone, double saldo) {
        super(cnpj, razaoSocial, nomeFantasia, dataDeAbertura, endereco, telefone);
        this._saldo = saldo;
    }

    public ContaInvestimentoPJ() {

    }

    //------------------ TO STRING ----------------------------


    @Override
    public String toString() {
        return "\nContaInvestimentoPJ {" +
                "\ncnpj: " + get_cnpj() +
                "\nrazaoSocial: " + get_razaoSocial() +
                "\nnomeFantasia: " + get_nomeFantasia() +
                "\ndataDeAbertura: " + get_dataDeAbertura() +
                "\nendereco: " + get_endereco() +
                "\ntelefone: " + get_telefone() +
                "\nagencia: " + get_agencia() +
                "\noperaçao: " + get_operacao() +
                "\nnumConta: " + get_numConta() +
                "\nsaldo: " + get_saldo() +
                "\n}";
    }

    //------------------------------ METODOS ---------------------------------------
    // VOID
    public void consultarSaldoVoid() {
        System.out.printf("%s - O saldo atual da conta Nº %s é: R$%.2f\n",
                getDataAtual(), get_numConta(), get_saldo());
    }

    public double consultarSaldo() {
        return get_saldo();
    }


    public double calcularCredito(double creditoValor) {
        set_saldo(get_saldo() - creditoValor - (creditoValor * 0.0299));
        return creditoValor;
    }

    public void calcularCreditoVoid(double creditovalor) {
        set_saldo(get_saldo() - creditovalor - (creditovalor * 0.0299));
        System.out.printf("\nO crédito de R$%.2f foi realizado com sucesso.\n", creditovalor);
        consultarSaldoVoid();
    }

    public double calcularDebito(double debitoValor) {
        set_saldo(get_saldo() - debitoValor);
        return debitoValor;
    }

    public void calcularDebitoVoid(double debitoValor) {
        if (get_saldo() < 0 || debitoValor > get_saldo()) {
            System.out.println("Você não tem saldo suficiente");
        } else {
            set_saldo(get_saldo() - debitoValor);
            System.out.printf("\nO débito de R$%.2f foi realizado com sucesso.\n", debitoValor);
            consultarSaldoVoid();
        }
    }

    public double calcularSaque(double saqueValor) {
        set_saldo(get_saldo() - saqueValor);
        return saqueValor;
    }

    public void calcularSaqueVoid(double saqueValor) {
        if (get_saldo() < 0 || saqueValor > get_saldo()) {
            System.out.println("Você não tem saldo suficiente");
        } else {
            set_saldo(get_saldo() - saqueValor);
            System.out.printf("\nO débito de R$%.2f foi realizado com sucesso.\n", saqueValor);
            consultarSaldoVoid();
        }
    }

    //------------------------ TERMINAR AS FUNÇÕES --------------------------


    public void abrirContaInvestimentoPJ() {
        perguntas();
        System.out.println("Digite o valor do depósito inicial:");
        _saldo = input.nextDouble();
    }

    public double depositar(double depositoValor) {
        set_saldo(depositoValor + get_saldo());
        return depositoValor;
    }

    public void depositarVoid(double depositoValor) {
        set_saldo(depositoValor + get_saldo());
        System.out.printf("\nO depósito de R$%.2f foi realizado com sucesso.\n", depositoValor);
        consultarSaldoVoid();
    }


    public double transferirValor(double transferenciaValor) {
        set_saldo(get_saldo() - transferenciaValor);
        return transferenciaValor;
    }

    public void transferirValorVoid(double transferenciaValor) {
        if (get_saldo() < 0 || transferenciaValor > get_saldo()) {
            System.out.println("Você não tem saldo suficiente");
        } else {
            set_saldo(get_saldo() - transferenciaValor);
            System.out.printf("\nA transferência  de R$%.2f foi realizado com sucesso.\n", transferenciaValor);
            consultarSaldoVoid();
        }
    }


    //-------------------------------- AJUSTAR ERROS ----------------------------------------------------
    public void mostrarOperacoes() {
        //------------------------------- OPERAÇÕES CONTA ----------------------------------
        System.out.println("------------------------------------------------");
        System.out.println("|       Qual operação você deseja fazer?       |");
        System.out.println("------------------------------------------------");
        System.out.println("|             1 - Saque                        |");
        System.out.println("|             2 - Deposito                     |");
        System.out.println("|             3 - Transferência                |");
        System.out.println("|             4 - Ver Saldo                    |");
        System.out.println("|                                              |");
        System.out.println("|             6 - SAIR                         |");
        System.out.println("------------------------------------------------");
        System.out.println("Selecione uma das opções acima");
        realizarOperacao();
    }

    public void realizarOperacao() {
        int operacao = input.nextInt();

        do {
            sair = false;
            try {
                switch (operacao) {
                    case 1 -> {
                        System.out.println("Você selecionou Saque");
                        System.out.println("Digite abaixo o valor que deseja Sacar:");
                        double valor = input.nextDouble();
                        calcularSaqueVoid(valor);
                        continuarPrograma();

                    }
                    case 2 -> {
                        System.out.println("Você selecionou Depósito");
                        System.out.println("Digite abaixo o valor que deseja Depositar:");
                        double valor = input.nextDouble();
                        depositarVoid(valor);
                        continuarPrograma();

                    }
                    case 3 -> {
                        System.out.println("Você selecionou Transferência");
                        System.out.println("Digite abaixo a AGÊNCIA DA CONTA que deseja transferir:");
                        String agTrasf = input.next();
                        System.out.println("Digite abaixo a OPERAÇÃO DA CONTA que deseja transferir:");
                        String opTransf = input.next();
                        System.out.println("Digite abaixo o NÚMERO DA CONTA que deseja transferir:");
                        String contaTransf = input.next();

                        System.out.println("Digite abaixo o valor que deseja Transferir:");
                        double valor = input.nextDouble();

                        System.out.printf("\nVocê tentou transferir R$%.2f para a conta de numero: AG %s OP %s CONTA %s.\n", valor, agTrasf, opTransf, contaTransf);
                        transferirValorVoid(valor);
                        continuarPrograma();

                    }
                    case 4 -> {
                        consultarSaldoVoid();
                        continuarPrograma();
                    }

                    case 6 -> {
                        sair = true;
                    }
                    default -> {
                        System.out.println("Digite uma opção válida");
                        mostrarOperacoes();
                    }

                }

            } catch (Exception e) {
                System.out.println("ERRO - Selecione uma opção válida");
                sair = true;
            }
        } while (!sair);


    }

    public void continuarPrograma() {
        sair = false;
        do {
            System.out.println("-------------------------------------------");
            System.out.println("|   Deseja realizar mais alguma operação? |");
            System.out.println("|             1 - Sim                     |");
            System.out.println("|             2 - Não                     |");
            System.out.println("-------------------------------------------");
            try {
                int continuarOp = input.nextInt();
                if (continuarOp == 1) {
                    sair = false;
                    mostrarOperacoes();
                } else if (continuarOp == 2) {
                    sair = true;
                } else {
                    System.out.println("Digite 1 para SIM ou 2 para NÃO");
                }
            } catch (Exception e) {
                System.out.println("ERRO: ESSA OPÇÃO NÃO EXISTE");
                sair = true;
            }
        } while (!sair);
    }
}