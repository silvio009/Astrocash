package com.example.Astrocash.models.porfolio;


import com.example.Astrocash.dto.portfolio.RegisterPortfolioDto;
import com.example.Astrocash.dto.portfolio.UpadatePortfolioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "portfolio")
public class PortfolioItem {

    @Id
    private String id;

    private String userId; // id do usuário dono da carteira
    private String ativoId; // id do ativo (ação ou cripto)

    private String tipoAtivo; // "STOCK" ou "CRYPTO"
    private double quantidade;
    private double precoMedioCompra;

    public PortfolioItem(RegisterPortfolioDto registerPortfolioDto) {
        // mudar com o token JWT
        userId = registerPortfolioDto.userId();
        ativoId = registerPortfolioDto.ativoId();


        tipoAtivo =registerPortfolioDto.tipoAtivo();
        quantidade = registerPortfolioDto.quantidade();
        precoMedioCompra =registerPortfolioDto.precoMedioCompra();

    }

    public void UpadateWith(UpadatePortfolioDto upadatePortfolioDto) {
        if (upadatePortfolioDto.tipoAtivo() != null){
            tipoAtivo = upadatePortfolioDto.tipoAtivo();
        }
        if (upadatePortfolioDto.quantidade() != null){
            quantidade = upadatePortfolioDto.quantidade();
        }
        if (upadatePortfolioDto.precoMedioCompra() != null){
            precoMedioCompra = upadatePortfolioDto.precoMedioCompra();
        }
    }
}
