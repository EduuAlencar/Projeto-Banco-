package ProjetoBanco;


import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class ContaCorrente extends PessoaFisica {
    //--------------------------- IMPORTS ---------------------------------------------------
    Scanner input = new Scanner(System.in);
    Random randomId = new Random();

    boolean sair;

    //-------------------------- ATRIBUTOS ----------------------------------------------------

    // GERAR NUMERO ALEATORIO PARA A CONTA
    int numeroContaAleatorio = randomId.nextInt(999999); // NUMERO DA CONTA CORRENTE
    private int digitoConta = randomId.nextInt(10); // DIGITO DA CONTA CORRENTE
    private String _numConta = String.valueOf(numeroContaAleatorio) + "-" + digitoConta;

    //------------------------- OUTROS ATRIBUTOS ------------------------------------------
    private Date _dataAtual = new Date();
    final private String _operacao = "001";
    final private String _agencia = "145";
    private double _saldo;
    private double _contaInvestimento;

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

    public void set_saldo(double saldo) {
        this._saldo = saldo;
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

    public double get_contaInvestimento() {
        return _contaInvestimento;
    }

    public void set_contaInvestimento(double contaInvestimento) {
        this._contaInvestimento = contaInvestimento;
    }

    //--------------------------- CONSTRUCTOR ------------------------------------------------
    public ContaCorrente(String nome, String sobrenome, String cpf, String documento, String dataDeNascimento,
                         String endereco, String telefone, double saldo) {
        super(nome, sobrenome, cpf, documento, dataDeNascimento, endereco, telefone);
        this._saldo = saldo;
    }

    public ContaCorrente() {

    }

    //------------------------------ TO STRING ----------------------------------

    @Override
    public String toString() {
        return "\nContaCorrente {" +
                "\nagencia: " + get_agencia() +
                "\noperaçao: " + get_operacao() +
                "\nnumConta: " + get_numConta() +
                "\nnome: " + get_nome() +
                "\nsobrenome:" + get_sobrenome() +
                "\ncpf: " + get_cpf() +
                "\ndocumento: " + get_documento() +
                "\ndataDeNascimento: " + get_dataDeNascimento() +
                "\nendereco: " + get_endereco() +
                "\ntelefone: " + get_telefone() +
                "\nsaldo: R$" + get_saldo() +
                "\ncontaInvestimento: R$" + get_contaInvestimento() +
                "\n}";
    }

    //------------------------------ METODOS ---------------------------------------

    public void consultarSaldoVoid() {
        System.out.printf("%s - O saldo atual da conta Nº %s é: R$%.2f\n" +
                        "                             - O saldo da sua conta investimento é: R$%.2f\n",
                getDataAtual(), get_numConta(), get_saldo(), get_contaInvestimento());
    }

    public double consultarSaldo() {
        return get_saldo();
    }


    // ------------ FUNÇÃO CARTAO DE CREDITO ---------------
    public double calcularCredito(double creditoValor) {
        set_saldo(get_saldo() - creditoValor - (creditoValor * 0.0399));
        return creditoValor;
    }

    public void calcularCreditoVoid(double creditovalor) {
        set_saldo(get_saldo() - creditovalor - (creditovalor * 0.0399));
        System.out.printf("\nO crédito de R$%.2f foi realizado com sucesso.\n", creditovalor);
        consultarSaldoVoid();
    }

    // ----------- FUNÇÃO CARTAO DE DEBITO -------------

    public double calcularDebito(double debitoValor) {
        set_saldo(get_saldo() - debitoValor);
        return debitoValor;
    }

    public void calcularDebitoVoid(double debito) {
        if (get_saldo() < 0 || debito > get_saldo()) {
            System.out.println("Você não tem saldo suficiente");
        } else {
            set_saldo(get_saldo() - debito);
            System.out.printf("\nO débito de R$%.2f foi realizado com sucesso.\n", debito);
            consultarSaldoVoid();
        }
    }

    public double calcularSaque(double saqueValor) {
        if (get_saldo() < 0 || saqueValor > get_saldo()) {
        } else {
            set_saldo(get_saldo() - saqueValor);
            consultarSaldoVoid();
        }
        return saqueValor;
    }

    public void calcularSaqueVoid(double saqueValor) {
        if (get_saldo() < 0 || saqueValor > get_saldo()) {
            System.out.println("Você não tem saldo suficiente");
        } else {
            set_saldo(get_saldo() - saqueValor);
            System.out.printf("\nO saque de R$%.2f foi realizado com sucesso.\n", saqueValor);
            consultarSaldoVoid();
        }
    }

    //------------ TERMINAR AS FUNÇÕES ------------
    public void abrirContaCorrente() {
        perguntas();
        System.out.println("Digite o valor do depósito inicial:");
        _saldo = input.nextDouble();
    }


    public double depositar(double depositoValor) {
        set_saldo(get_saldo() + depositoValor);
        return depositoValor;
    }

    public void depositarVoid(double depositoValor) {
        set_saldo(get_saldo() + depositoValor);
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
            System.out.printf("\nA transferência de R$%.2f foi realizado com sucesso.\n", transferenciaValor);
            consultarSaldoVoid();
        }
    }

    public double investirValor(double investimentoValor) {
        set_saldo(get_saldo() - investimentoValor);
        set_contaInvestimento(get_contaInvestimento() + investimentoValor);
        return investimentoValor;
    }

    public void investirValorVoid(double investimentoValor) {
        if (get_saldo() < 0 || investimentoValor > get_saldo()) {
            System.out.println("Você não tem saldo suficiente");
        } else {
            set_saldo(get_saldo() - investimentoValor);
            set_contaInvestimento(get_contaInvestimento() + investimentoValor);
            System.out.printf("\nA transferência de R$%.2f foi realizado com sucesso.\n", investimentoValor);
            consultarSaldoVoid();
        }
    }

    public void mostrarOperacoes() {
        //------------------------------- OPERAÇÕES CONTA ----------------------------------
        System.out.println("------------------------------------------------");
        System.out.println("|       Qual operação você deseja fazer?       |");
        System.out.println("------------------------------------------------");
        System.out.println("|             1 - Saque                        |");
        System.out.println("|             2 - Deposito                     |");
        System.out.println("|             3 - Transferência                |");
        System.out.println("|             4 - Investimento                 |");
        System.out.println("|             5 - Ver Saldo                    |");
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
                        System.out.println("Você selecionou Investimento");
                        System.out.println("Digite abaixo o valor que deseja Investir:");
                        double valor = input.nextDouble();
                        investirValorVoid(valor);
                        continuarPrograma();
                    }
                    case 5 -> {
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
