import './Login.style';
import { LoginWrapper, InputField, Links, LoginButton, LoginContainer, LoginForm, Options, SocialButton, SocialLogin, Title } from "./Login.style";


function Login() {
    return (
        <LoginWrapper>
            <LoginContainer>
                <Title>로그인</Title>
                <LoginForm>
                    <InputField placeholder="아이디" />
                    <InputField type="password" placeholder="비밀번호" />
                    <LoginButton type="submit">로그인</LoginButton>
                </LoginForm>
                <Options>
                    <label>
                        <input type="checkbox" />
                        자동 로그인
                    </label>
                    <Links>
                        <a href="#">아이디 찾기</a> | <a href="#">비밀번호 찾기</a>
                    </Links>
                </Options>
                <SocialLogin>
                    <SocialButton className="google-login">
                        <img src="https://img.icons8.com/?size=100&id=17949&format=png&color=000000" alt="Google" />
                        구글 로그인
                    </SocialButton>
                    <SocialButton className="kakao-login">
                        <img src="https://img.icons8.com/?size=100&id=2951&format=png&color=000000" alt="Kakao" />
                        카카오 로그인
                    </SocialButton>
                </SocialLogin>
            </LoginContainer>
        </LoginWrapper>
    )
}

export default Login;