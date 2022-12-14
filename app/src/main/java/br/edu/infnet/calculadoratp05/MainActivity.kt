package br.edu.infnet.calculadoratp05

import android.app.Activity
import android.os.Bundle
import br.edu.infnet.calculadoratp05.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupOnClickListener()
    }

    private fun setupOnClickListener() {
        binding.btn0.setOnClickListener { AcrescentarUmaExpressao("0", true) }
        binding.btn1.setOnClickListener { AcrescentarUmaExpressao("1", true) }
        binding.btn2.setOnClickListener { AcrescentarUmaExpressao("2", true) }
        binding.btn3.setOnClickListener { AcrescentarUmaExpressao("3", true) }
        binding.btn4.setOnClickListener { AcrescentarUmaExpressao("4", true) }
        binding.btn5.setOnClickListener { AcrescentarUmaExpressao("5", true) }
        binding.btn6.setOnClickListener { AcrescentarUmaExpressao("6", true) }
        binding.btn7.setOnClickListener { AcrescentarUmaExpressao("7", true) }
        binding.btn8.setOnClickListener { AcrescentarUmaExpressao("8", true) }
        binding.btn9.setOnClickListener { AcrescentarUmaExpressao("9", true) }

        binding.btnPonto.setOnClickListener { AcrescentarUmaExpressao(".", true) }

        // Operadores:
        binding.btnSoma.setOnClickListener { AcrescentarUmaExpressao("+", false) }
        binding.btnSubtracao.setOnClickListener { AcrescentarUmaExpressao("-", false) }
        binding.btnMultiplicacao.setOnClickListener { AcrescentarUmaExpressao("*", false) }
        binding.btnDivisao.setOnClickListener { AcrescentarUmaExpressao("/", false) }

        binding.btnLimpar.setOnClickListener {
            binding.tvExpressao.text = ""
            binding.tvResultado.text = ""
        }

        binding.btnBackspace.setOnClickListener {

            val string = binding.tvExpressao.text.toString()

            if(string.isNotBlank()){
                binding.tvExpressao.text = string.substring(0, string.length-1)
            }
            binding.tvResultado.text = ""

        }

        binding.btnIgual.setOnClickListener {

            try {
                val expressao = ExpressionBuilder(binding.tvExpressao.text.toString()).build()
                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if(resultado == longResult.toDouble())
                    binding.tvResultado.text = longResult.toString()
                else
                    binding.tvResultado.text = resultado.toString()

            } catch(_: Exception) {

            }

        }

    }

    private fun AcrescentarUmaExpressao(string: String, limpar_dados: Boolean) {

        if (binding.tvResultado.text.isNotEmpty()){
           binding.tvExpressao.text = ""
        }

        if (limpar_dados){
            binding.tvResultado.text = ""
            binding.tvExpressao.append(string)
        } else{
            binding.tvExpressao.append(binding.tvResultado.text)
            binding.tvExpressao.append(string)
            binding.tvResultado.text = ""
        }

    }

}